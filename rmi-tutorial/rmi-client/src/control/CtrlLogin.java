package control;

import java.rmi.RemoteException;

import model.LoginTO;
import model.User;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import base.Compute;

/**
 * Classe que possui os controles de login, extende RmiStarter para que sua localização seja explicitada para acesso do RMI
 */
public class CtrlLogin {


    /**
     * Método que acessa as informações do usuario no Login
     * @param loginTO
     * @param compute
     * @return
     */
    public User doLogin(LoginTO loginTO, Compute compute){
    	User user = null;
    	if ((loginTO != null) && (compute != null))
    	{
			try {
				loginTO.setCompute(compute);
				loginTO = compute.executeLogin(loginTO);
	   	 		user = loginTO.getUser();
				//System.out.println("User Name: " + user.getRealName() + "\nEmail: " + user.getEmail());
	 			//System.out.println("Message: " + loginTO.getErrorMessage());
	   	 		compute.sendMessage("macaca");
			} catch (RemoteException e){
				System.out.println("Message: " + loginTO.getErrorMessage() + "\nException: " + e.toString());
			}
    	}
    	return user;
    }
    
    public User twLogin(Twitter twitter, String userLogin, String userPassword){
    	User user = null;
    	try {
			AccessToken at = twitter.getOAuthAccessToken(userLogin, userPassword);
			twitter = new TwitterFactory().getInstance(at);
			twitter4j.User usr = twitter.showUser(twitter.getId());
			//cannot get the user email through API --> http://stackoverflow.com/questions/3599621/is-there-a-way-to-get-an-users-email-id-after-verifying-her-twitter-identity-us
//			user = new User(twitter.getId(), null, usr.getName(), twitter.getScreenName(), usr.getCreatedAt(), usr.isProtected(), following, tuites, myTuites, followers)
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return user;
    }


}
