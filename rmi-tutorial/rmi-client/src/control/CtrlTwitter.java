package control;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import model.User;
import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class CtrlTwitter {
	
	private Twitter twitter;
	
	public CtrlTwitter(Twitter twitter){
		this.twitter = twitter;
	}
    
    public User twLogin(String userLogin, String userPassword){
    	User user = null;
    	try {

//			twitter = new TwitterFactory().getInstance(userLogin, userPassword);

			twitter4j.User u = twitter.verifyCredentials();
			//cannot get the user email through API --> http://stackoverflow.com/questions/3599621/is-there-a-way-to-get-an-users-email-id-after-verifying-her-twitter-identity-us
			
			user = new User(u.getId(), u.getScreenName(), u.getName(), null, null, null, new ImageIcon(u.getProfileImageURL()));
			

			
			System.out.println("Consegui logar no twitter ---> login: " + twitter.getScreenName());
			
			getFollowers(twitter);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return user;
    }
    
    public ArrayList<User> getFollowers(Twitter t){
    	ArrayList<User> alFollowers = new ArrayList<User>();
    	
		try {
			IDs ids = twitter.getFollowersIDs();

		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return alFollowers;
    }
}
