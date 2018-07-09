package com.homedepot.springbootdocker.dao;



import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.spanner.*;
import com.homedepot.springbootdocker.models.User;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


public class UserDao {

    // Name of your instance & database.
    private static final String instanceId = "testspringbootdocker";
    private static final String databaseId = "test_db";

    private static DatabaseClient databaseClient;

    private static DatabaseClient getDatabaseClient() throws Exception{
        if(databaseClient == null) {
            SpannerOptions options = SpannerOptions.newBuilder().build();
            Spanner spanner = options.getService();
            try {

                databaseClient = spanner.getDatabaseClient(DatabaseId.of(
                        options.getProjectId(), instanceId, databaseId));
            } catch (Exception e) {
                System.out.print("Error while initiating client");
                throw e;
            }
        }
        return databaseClient;
    }

    public boolean checkUserExistence(User user) throws Exception{
        boolean isUserExists = false;
        try {
            getDatabaseClient();
            //Reading code
            StringBuilder dynamicQuery = new StringBuilder();
            dynamicQuery.append("SELECT * FROM user where\n")
                    .append("username=" + "'" + user.getUserName() + "'" + "\n")
                    .append("and password=" + "'" + user.getPassword() + "'");

            System.out.println("query -->" + dynamicQuery.toString());

            ResultSet resultSet = databaseClient.singleUse().executeQuery(Statement.of(dynamicQuery.toString()));
            System.out.println("\n\nResults:");

            while (resultSet.next()) {
                isUserExists = true;
                break;
            }

            resultSet.close();
        }
        catch(Exception e){
            System.out.println("Error occurred while checking user existance");
            throw e;
        }
        return isUserExists;
    }

    public boolean createUser(User user) throws Exception{
        boolean userCreate = false;
        getDatabaseClient();
        //Insert part...
        List<User> userList  = new ArrayList<User>();
        userList.add(user);

        List<Mutation> mutations = new ArrayList<Mutation>();
        for(User users:userList) {

            mutations.add(Mutation.newInsertBuilder("user")
                    .set("username")
                    .to(users.getUserName())
                    .set("password")
                    .to(users.getPassword())
                    .build());

        }
        try {
            databaseClient.write(mutations);
        }catch(Exception e){
            System.out.println("Error Occurred while creating user");
        }
        userCreate = true;

        return userCreate;
    }


}

