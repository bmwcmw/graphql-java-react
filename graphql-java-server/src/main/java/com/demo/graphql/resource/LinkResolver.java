package com.demo.graphql.resource;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.demo.graphql.entity.Link;
import com.demo.graphql.entity.User;

/**
 * Contains Link sub-queries
 */
class LinkResolver implements GraphQLResolver<Link> {
    
    private final UserRepository userRepository;

    LinkResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User postedBy(Link link) {
        if (link.getUserId() == null) {
            return null;
        }
        return userRepository.findById(link.getUserId());
    }
}
