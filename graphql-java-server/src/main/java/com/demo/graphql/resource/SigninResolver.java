package com.demo.graphql.resource;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.demo.graphql.entity.SigninPayload;
import com.demo.graphql.entity.User;

/**
 * Contains SigninPayload sub-queries
 */
class SigninResolver implements GraphQLResolver<SigninPayload> {

    public User user(SigninPayload payload) {
        return payload.getUser();
    }
}
