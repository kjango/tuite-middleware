package view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.InputStream;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.LoginTO;
import model.User;
import twitter4j.AccountSettings;
import twitter4j.AccountTotals;
import twitter4j.Category;
import twitter4j.DirectMessage;
import twitter4j.Friendship;
import twitter4j.GeoLocation;
import twitter4j.GeoQuery;
import twitter4j.IDs;
import twitter4j.Location;
import twitter4j.PagableResponseList;
import twitter4j.Paging;
import twitter4j.Place;
import twitter4j.ProfileImage;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.RateLimitStatusListener;
import twitter4j.RelatedResults;
import twitter4j.Relationship;
import twitter4j.ResponseList;
import twitter4j.SavedSearch;
import twitter4j.SimilarPlaces;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterAPIConfiguration;
import twitter4j.TwitterException;
import twitter4j.UserList;
import twitter4j.ProfileImage.ImageSize;
import twitter4j.api.HelpMethods.Language;
import twitter4j.auth.AccessToken;
import twitter4j.auth.Authorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import base.Compute;
import control.CtrlLogin;
import control.CtrlRMI;

public class LoginScreen extends javax.swing.JFrame{

	private JTextField loginField;
	private JPasswordField passwordField;
	private Compute compute;
	private CtrlRMI ctrlRMI;
	private JFrame me;
	private JRadioButton rdbtnTuiter;
	private JRadioButton rdbtnTwitter;
	private JButton btnRegister;
	private JButton btnLogin;
	private JButton btnQuit;
	
	private Twitter twitter = new Twitter() {
		
		@Override
		public boolean test() throws TwitterException {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public ResponseList<Language> getLanguages() throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public TwitterAPIConfiguration getAPIConfiguration()
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public RelatedResults getRelatedResults(long arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getTermsOfService() throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getPrivacyPolicy() throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Place> searchPlaces(GeoQuery arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Place> reverseGeoCode(GeoQuery arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public SimilarPlaces getSimilarPlaces(GeoLocation arg0, String arg1,
				String arg2, String arg3) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Place getGeoDetails(String arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Place createPlace(String arg0, String arg1, String arg2,
				GeoLocation arg3, String arg4) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Trends getLocationTrends(int arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Location> getAvailableTrends(GeoLocation arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Location> getAvailableTrends() throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public SavedSearch showSavedSearch(int arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<SavedSearch> getSavedSearches() throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public SavedSearch destroySavedSearch(int arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public SavedSearch createSavedSearch(String arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User reportSpam(String arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User reportSpam(long arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IDs getBlockingUsersIDs() throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<twitter4j.User> getBlockingUsers(int arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<twitter4j.User> getBlockingUsers()
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public boolean existsBlock(long arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean existsBlock(String arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public twitter4j.User destroyBlock(long arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User destroyBlock(String arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User createBlock(long arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User createBlock(String arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User enableNotification(long arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User enableNotification(String arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User disableNotification(long arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User disableNotification(String arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getFavorites(String arg0, Paging arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getFavorites(String arg0, int arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getFavorites(Paging arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getFavorites(String arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getFavorites(int arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getFavorites() throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Status destroyFavorite(long arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Status createFavorite(long arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User verifyCredentials() throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User updateProfileImage(InputStream arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User updateProfileImage(File arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User updateProfileColors(String arg0, String arg1,
				String arg2, String arg3, String arg4) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User updateProfileBackgroundImage(InputStream arg0,
				boolean arg1) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User updateProfileBackgroundImage(File arg0, boolean arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User updateProfile(String arg0, String arg1, String arg2,
				String arg3) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public AccountSettings updateAccountSettings(Integer arg0, Boolean arg1,
				String arg2, String arg3, String arg4, String arg5)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public RateLimitStatus getRateLimitStatus() throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public AccountTotals getAccountTotals() throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public AccountSettings getAccountSettings() throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IDs getFriendsIDs(String arg0, long arg1) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IDs getFriendsIDs(long arg0, long arg1) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IDs getFriendsIDs(long arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IDs getFollowersIDs(String arg0, long arg1) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IDs getFollowersIDs(long arg0, long arg1) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IDs getFollowersIDs(long arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Relationship updateFriendship(long arg0, boolean arg1, boolean arg2)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Relationship updateFriendship(String arg0, boolean arg1, boolean arg2)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Relationship showFriendship(long arg0, long arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Relationship showFriendship(String arg0, String arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Friendship> lookupFriendships(long[] arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Friendship> lookupFriendships(String[] arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IDs getOutgoingFriendships(long arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IDs getNoRetweetIds() throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IDs getIncomingFriendships(long arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public boolean existsFriendship(String arg0, String arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public twitter4j.User destroyFriendship(long arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User destroyFriendship(String arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User createFriendship(long arg0, boolean arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User createFriendship(String arg0, boolean arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User createFriendship(long arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User createFriendship(String arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public DirectMessage showDirectMessage(long arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public DirectMessage sendDirectMessage(long arg0, String arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public DirectMessage sendDirectMessage(String arg0, String arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<DirectMessage> getSentDirectMessages(Paging arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<DirectMessage> getSentDirectMessages()
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<DirectMessage> getDirectMessages(Paging arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<DirectMessage> getDirectMessages()
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public DirectMessage destroyDirectMessage(long arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User showUserListSubscription(int arg0, long arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public PagableResponseList<twitter4j.User> getUserListSubscribers(int arg0,
				long arg1) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public UserList destroyUserListSubscription(int arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public UserList createUserListSubscription(int arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User showUserListMembership(int arg0, long arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public PagableResponseList<twitter4j.User> getUserListMembers(int arg0,
				long arg1) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public UserList deleteUserListMember(int arg0, long arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public UserList addUserListMembers(int arg0, String[] arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public UserList addUserListMembers(int arg0, long[] arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public UserList addUserListMember(int arg0, long arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public UserList updateUserList(int arg0, String arg1, boolean arg2,
				String arg3) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public UserList showUserList(int arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public PagableResponseList<UserList> getUserLists(long arg0, long arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public PagableResponseList<UserList> getUserLists(String arg0, long arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public PagableResponseList<UserList> getUserListSubscriptions(String arg0,
				long arg1) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getUserListStatuses(int arg0, Paging arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public PagableResponseList<UserList> getUserListMemberships(long arg0,
				long arg1, boolean arg2) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public PagableResponseList<UserList> getUserListMemberships(String arg0,
				long arg1, boolean arg2) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public PagableResponseList<UserList> getUserListMemberships(String arg0,
				long arg1) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public PagableResponseList<UserList> getUserListMemberships(long arg0,
				long arg1) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public PagableResponseList<UserList> getUserListMemberships(long arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<UserList> getAllUserLists(long arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<UserList> getAllUserLists(String arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public UserList destroyUserList(int arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public UserList createUserList(String arg0, boolean arg1, String arg2)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User showUser(long arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public twitter4j.User showUser(String arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<twitter4j.User> searchUsers(String arg0, int arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<twitter4j.User> lookupUsers(long[] arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<twitter4j.User> lookupUsers(String[] arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<twitter4j.User> getUserSuggestions(String arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Category> getSuggestedUserCategories()
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ProfileImage getProfileImage(String arg0, ImageSize arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<twitter4j.User> getMemberSuggestions(String arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Status updateStatus(StatusUpdate arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Status updateStatus(String arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Status showStatus(long arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Status retweetStatus(long arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getRetweets(long arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IDs getRetweetedByIDs(long arg0, Paging arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public IDs getRetweetedByIDs(long arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<twitter4j.User> getRetweetedBy(long arg0, Paging arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<twitter4j.User> getRetweetedBy(long arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Status destroyStatus(long arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getUserTimeline(long arg0, Paging arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getUserTimeline(String arg0, Paging arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getUserTimeline(Paging arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getUserTimeline(long arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getUserTimeline(String arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getUserTimeline() throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getRetweetsOfMe(Paging arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getRetweetsOfMe() throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getRetweetedToUser(long arg0, Paging arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getRetweetedToUser(String arg0, Paging arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getRetweetedToMe(Paging arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getRetweetedToMe() throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getRetweetedByUser(long arg0, Paging arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getRetweetedByUser(String arg0, Paging arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getRetweetedByMe(Paging arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getRetweetedByMe() throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getMentions(Paging arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getMentions() throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getHomeTimeline(Paging arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Status> getHomeTimeline() throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Trends> getWeeklyTrends(Date arg0, boolean arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Trends> getWeeklyTrends() throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Trends> getDailyTrends(Date arg0, boolean arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ResponseList<Trends> getDailyTrends() throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public QueryResult search(Query arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void shutdown() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public String getScreenName() throws TwitterException,
				IllegalStateException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public long getId() throws TwitterException, IllegalStateException {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public Configuration getConfiguration() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Authorization getAuthorization() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void addRateLimitStatusListener(RateLimitStatusListener arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void setOAuthConsumer(String arg0, String arg1) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void setOAuthAccessToken(AccessToken arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public RequestToken getOAuthRequestToken(String arg0, String arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public RequestToken getOAuthRequestToken(String arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public RequestToken getOAuthRequestToken() throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public AccessToken getOAuthAccessToken(String arg0, String arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public AccessToken getOAuthAccessToken(RequestToken arg0, String arg1)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public AccessToken getOAuthAccessToken(RequestToken arg0)
				throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public AccessToken getOAuthAccessToken(String arg0) throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public AccessToken getOAuthAccessToken() throws TwitterException {
			// TODO Auto-generated method stub
			return null;
		}
	};

	/**
	 * Launch the application.
	 */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginScreen().setVisible(true);
            }
        });
    }

	/**
	 * Create the application.
	 */
	public LoginScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		ctrlRMI = new CtrlRMI();
		compute = ctrlRMI.getCompute();
		me = this;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setMinimumSize(new Dimension(354, 205));
		setLocationByPlatform(true);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent arg0) {
			}
		});
		setTitle("Tuiter");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 328, 155);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLogin.setBounds(35, 25, 35, 22);
		panel.add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(10, 57, 60, 22);
		panel.add(lblPassword);
		
		loginField = new JTextField();
		loginField.setBounds(80, 27, 217, 20);
		panel.add(loginField);
		loginField.setColumns(10);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean ok = true;
				if (loginField.getText().isEmpty()){
					ok = false;
				}
				if (passwordField.getText().isEmpty()){
					ok = false;
				}
				if(ok){
					User user = null;
					CtrlLogin ctrlLogin = new CtrlLogin();
					//using Twitter
					if (rdbtnTwitter.isSelected()){
						user = ctrlLogin.twLogin(twitter, loginField.getText(), passwordField.getText());
						
					}else{	
						//TODO verificar esse password.string
						LoginTO loginTO = new LoginTO(loginField.getText(), passwordField.getPassword().toString());
						user = ctrlLogin.doLogin(loginTO, compute);
					}
					
					if (user != null){
						MainScreen ms = new MainScreen(user,compute);
						ms.setVisible(true);
						dispose();
					}
					else{
						JOptionPane.showMessageDialog(null, "Invalid login or password", "Warning!", 0);
					}
				}

			}
		});
		btnLogin.setBounds(10, 121, 89, 23);
		panel.add(btnLogin);
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterScreen rs = new RegisterScreen(compute, me);
				rs.setVisible(true);
				setEnabled(false);
			}
		});
		btnRegister.setBounds(109, 121, 89, 23);
		panel.add(btnRegister);
		
		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnQuit.setBounds(208, 121, 89, 23);
		panel.add(btnQuit);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(80, 59, 217, 20);
		panel.add(passwordField);
		
		ButtonGroup buttonGroup = new ButtonGroup();
				
		rdbtnTuiter = new JRadioButton("Tuiter");
		rdbtnTuiter.setBounds(20, 91, 109, 23);
		panel.add(rdbtnTuiter);
		buttonGroup.add(rdbtnTuiter);
		rdbtnTuiter.setSelected(true);
		
		rdbtnTwitter = new JRadioButton("Twitter");
		rdbtnTwitter.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (rdbtnTwitter.isSelected()){
					btnRegister.setEnabled(false);
				}else{
					btnRegister.setEnabled(true);
				}
			}
		});
		rdbtnTwitter.setBounds(188, 91, 109, 23);
		panel.add(rdbtnTwitter);
		buttonGroup.add(rdbtnTwitter);
		
	}
}
