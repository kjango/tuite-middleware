package control;

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

	public CtrlTwitter(Twitter twitter) {
		this.twitter = twitter;
	}

	public User twLogin() {
		User user = null;
		try {
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

			twitter4j.User u = twitter.verifyCredentials();

			// user = getFullUser(u);
			return getFullUser(u);

		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
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
		java.sql.Timestamp date = new java.sql.Timestamp(status
				.getCreatedAt().getTime());

		return new Tuite((int) status.getId(), status.getText(), date, user);
	}
	
	public Tuite getTuite(Tweet status){
//		User user = getSimpleUser(status.getFromUserId());
		User user = new User(status.getFromUserId(), status.getFromUserName(), new ImageIcon(status.getProfileImageUrl()));
		java.sql.Timestamp date = new java.sql.Timestamp(status
				.getCreatedAt().getTime());

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

	public User getFullUser(twitter4j.User twUser) {
		return new User(twUser.getId(), twUser.getScreenName(),
				twUser.getName(), getFollowing(), getTimeline(),
				getFollowers(), new ImageIcon(twUser.getProfileImageURL()));
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

	public User refreshUser(User user) {
		try {
			return getFullUser(twitter.verifyCredentials());
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return null;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alTuite;
	}
	
	public ArrayList<User> searchPeople(String text) {
		ArrayList<User> alUsr = new ArrayList<User>();
		
		try {
//			Query query = new Query(text);
			ResponseList<twitter4j.User> result;
			result = twitter.searchUsers(text, 10);
			for (twitter4j.User usr : result) {
				alUsr.add(new User(usr.getId(), usr.getScreenName(), usr.getName(), null, null, null, new ImageIcon(usr.getProfileImageURL())));
			}

		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alUsr;
	}

}
