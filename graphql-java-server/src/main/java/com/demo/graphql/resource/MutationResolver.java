package com.demo.graphql.resource;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.demo.graphql.entity.AuthContext;
import com.demo.graphql.entity.AuthData;
import com.demo.graphql.entity.Link;
import com.demo.graphql.entity.SigninPayload;
import com.demo.graphql.entity.User;
import com.demo.graphql.entity.Vote;
import graphql.GraphQLException;
import graphql.schema.DataFetchingEnvironment;

import java.time.Instant;
import java.time.ZoneOffset;

/**
 * Mutation root
 */
class MutationResolver implements GraphQLResolver {

    private final LinkRepository linkRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;

    public MutationResolver(LinkRepository linkRepository, UserRepository userRepository, VoteRepository voteRepository) {
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    public Link createLink(String url, String description, DataFetchingEnvironment env) {
        AuthContext context = env.getContext();
        Link newLink = new Link(url, description, context.getUser().getId());
        linkRepository.saveLink(newLink);
        return newLink;
    }

    public User createUser(String name, AuthData auth) {
        User newUser = new User(name, auth.getEmail(), auth.getPassword());
        return userRepository.saveUser(newUser);
    }

    public SigninPayload signinUser(AuthData auth) {
        User user = userRepository.findByEmail(auth.getEmail());
        if (user.getPassword().equals(auth.getPassword())) {
            return new SigninPayload(user.getId(), user);
        }
        throw new GraphQLException("Invalid credentials");
    }

    public Vote createVote(String linkId, String userId) {
        return voteRepository.saveVote(new Vote(Instant.now().atZone(ZoneOffset.UTC), userId, linkId));
    }
}
