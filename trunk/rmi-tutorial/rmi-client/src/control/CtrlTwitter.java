package control;

import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import model.Tuite;
import model.User;
import twitter4j.IDs;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class CtrlTwitter {

	private Twitter twitter;

	public CtrlTwitter() {
//		this.twitter = twitter;
	}

	public User twLogin() {
//		try {
			try {
				twitter = new TwitterFactory().getInstance();
				try {
					RequestToken requestToken = twitter.getOAuthRequestToken();
					AccessToken accessToken = null;
					if (null == accessToken) {
						String pin = JOptionPane.showInputDialog("Open the following URL and grant access to your account:\n"
								+ "\n And then enter the pin number (if available) or just click ok.", requestToken.getAuthorizationURL());
						
						try {
				         if(pin.length() > 0){
				             accessToken = twitter.getOAuthAccessToken(requestToken, pin);
				           }else{
				             accessToken = twitter.getOAuthAccessToken();
				           }
						} catch (TwitterException te) {
							if (401 == te.getStatusCode()) {
								System.out.println("Unable to get the access token.");
							} else {
								te.printStackTrace();
							}
						}
					}
					
					if(null == accessToken){
						return null; 
					}
					
					System.out.println("Got access token.");
					System.out.println("Access token: "
							+ accessToken.getToken());
					System.out.println("Access token secret: "
							+ accessToken.getTokenSecret());
				} catch (IllegalStateException ie) {
					// access token is already available, or consumer key/secret
					// is not set.
					if (!twitter.getAuthorization().isEnabled()) {
						System.out
								.println("OAuth consumer key/secret is not set.");
						return null;
					}
				}

			} catch (TwitterException te) {
				te.printStackTrace();
				System.out
						.println("Failed to get timeline: " + te.getMessage());
			}

			// cannot get the user email through API -->
			// http://stackoverflow.com/questions/3599621/is-there-a-way-to-get-an-users-email-id-after-verifying-her-twitter-identity-us

//			twitter4j.User u = twitter.verifyCredentials();

			return getFullUser();

//		} catch (TwitterException e) {
//			e.printStackTrace();
//		}

	}

	public ArrayList<User> getFollowers() {
		ArrayList<User> alFollowers = new ArrayList<User>();

		try {
			IDs ids = twitter.getFollowersIDs(twitter.getId(), -1);

			for (long id : ids.getIDs()) {
				alFollowers.add(getSimpleUser(id));
			}

		} catch (TwitterException e) {
			e.printStackTrace();
		}

		return alFollowers;
	}
	
	public User getFollowers(User user) {
		ArrayList<User> alFollowers = new ArrayList<User>();

		try {
			IDs ids = twitter.getFollowersIDs(user.getId(), -1);

			for (long id : ids.getIDs()) {
				alFollowers.add(getSimpleUser(id));
			}

		} catch (TwitterException e) {
			e.printStackTrace();
		}
		user.setFollowers(alFollowers);
		return user;
	}

	// following = friends
	public ArrayList<User> getFollowing() {
		ArrayList<User> alFollowing = new ArrayList<User>();

		try {
			IDs ids = twitter.getFriendsIDs(twitter.getId(), -1);

			for (long id : ids.getIDs()) {
				alFollowing.add(getSimpleUser(id));
			}

		} catch (TwitterException e) {
			e.printStackTrace();
		}

		return alFollowing;
	}
	
	// following = friends
	public User getFollowing(User user) {
		ArrayList<User> alFollowing = new ArrayList<User>();

		try {
			IDs ids = twitter.getFriendsIDs(user.getId(), -1);

			for (long id : ids.getIDs()) {
				alFollowing.add(getSimpleUser(id));
			}

		} catch (TwitterException e) {
			e.printStackTrace();
		}
		user.setFollowing(alFollowing);
		return user;
	}

	public Tuite twittar(String text) {
		Status status;
		try {
			status = twitter.updateStatus(text);
			return getTuite(status);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Tuite getTuite(Status status){
		User user = new User(status.getUser().getId(), status.getUser().getName(), new ImageIcon(status.getUser().getProfileImageURL()));
		java.sql.Timestamp date = new java.sql.Timestamp(status.getCreatedAt().getTime());

		return new Tuite((int) status.getId(), status.getText(), date, user);
	}
	
	public Tuite getTuite(Tweet status){
		User user = new User(status.getFromUserId(), status.getFromUserName(), new ImageIcon(status.getProfileImageUrl()));
		java.sql.Timestamp date = new java.sql.Timestamp(status.getCreatedAt().getTime());

		return new Tuite((int) status.getId(), status.getText(), date, user);
	}

	public User getSimpleUser(long id) {
		twitter4j.User twUser;
		try {
			twUser = twitter.showUser(id);

			return new User(twUser.getId(), twUser.getScreenName(),
					twUser.getName(), null, null, null, new ImageIcon(
							twUser.getProfileImageURL()));

		} catch (TwitterException e) {
			e.printStackTrace();
		}

		return null;
	}


	public User getFullUser() {
		twitter4j.User twUser;
		try {
			twUser = twitter.verifyCredentials();
			return new User(twUser.getId(), twUser.getScreenName(),
					twUser.getName(), getFollowing(), getTimeline(),
					getFollowers(), new ImageIcon(twUser.getProfileImageURL()));
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Tuite> getTimeline() {
		ArrayList<Tuite> timeline = new ArrayList<Tuite>();

		try {
			for (Status status : twitter.getHomeTimeline()) {
				timeline.add(getTuite(status));
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}

		return timeline;
	}

	public boolean doesFollow(User a, User b) {
		// TODO
		for (User u : a.getFollowing()) {
			if (u.getId() == b.getId()) {
				return true;
			}
		}

		return false;
	}

	public ArrayList<Tuite> searchTweets(String text) {
		ArrayList<Tuite> alTuite = new ArrayList<Tuite>();
		
		try {
			Query query = new Query(text);
			QueryResult result;
			result = twitter.search(query);
			for (Tweet tweet : result.getTweets()) {
				alTuite.add(getTuite(tweet));
			}

		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return alTuite;
	}
	
	public ArrayList<User> searchPeople(String text) {
		ArrayList<User> alUsr = new ArrayList<User>();
		
		try {
			ResponseList<twitter4j.User> result;
			result = twitter.searchUsers(text, 10);
			for (twitter4j.User usr : result) {
				alUsr.add(new User(usr.getId(), usr.getScreenName(), usr.getName(), null, null, null, new ImageIcon(usr.getProfileImageURL())));
			}

		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return alUsr;
	}

	public void changeProfileImage(File image) {
		try {
			twitter.updateProfileImage(image);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	public void changeRealName(String text) {
		try {
			twitter.updateProfile(text, null, null, null);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
	}

	public User getUserTimeline(User user) {
		try {
			ResponseList<Status> rl = twitter.getUserTimeline(user.getId());
			for (Status status : rl) {
				user.addTuite(getTuite(status), 0);
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return user;
	}

	public ArrayList<User> getNotifications() {
		ArrayList<User> alUsr = new ArrayList<User>();
		
		try {
			IDs ids;
			ids = twitter.getOutgoingFriendships(twitter.getId());
			for (long id : ids.getIDs()) {
				alUsr.add(getSimpleUser(id));
			}

		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return alUsr;
	}
	
	public User follow(User user, User followed){
		try {
			twitter4j.User twUser = twitter.createFriendship(followed.getId());
			user.addFollowing(new User(twUser.getId(), twUser.getScreenName(),
					twUser.getName(), null, null, null, new ImageIcon(
							twUser.getProfileImageURL())));
			
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public User unFollow(User user, User unFollowed){
		try {
			twitter.destroyFriendship(unFollowed.getId());
			user.removeFollowing(unFollowed);
			
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
		return user;
	}
}
