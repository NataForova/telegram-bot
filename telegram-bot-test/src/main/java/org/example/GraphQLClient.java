package org.example;
import com.apollographql.apollo.ApolloClient;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GraphQLClient {
    @Value("${telegram.server-url}")
    private String serverURL;
    private final ApolloClient apolloClient;

    public GraphQLClient() {
        this.apolloClient = ApolloClient.builder()
                .serverUrl(serverURL)
                .build();
    }

    /*public void signUp(String email, String password) {
        SignUpMutation signUpMutation = SignUpMutation.builder()
                .credentials(LoginInput.builder().email(email).password(password).build())
                .build();

        Disposable disposable = Rx2Apollo.from(apolloClient.mutate(signUpMutation))
                .subscribe(response -> handleSignUpResponse(response),
                        throwable -> System.out.println("Error occurred: " + throwable.getMessage()));
    }

    private void handleSignUpResponse(Response<SignUpMutation.Data> response) {
        if (response.getData() != null && response.getData().signUp() != null) {
            System.out.println("SignUp successful: " + response.getData().signUp().toString());
        } else {
            System.out.println("SignUp failed.");
        }
    }*/

}
