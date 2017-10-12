package com.demo.graphql.resource;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.demo.graphql.entity.Link;
import com.demo.graphql.entity.LinkFilter;

import java.util.List;

/**
 * Query root. Contains top-level queries.
 */
class QueryResolver implements GraphQLQueryResolver {

    private final LinkRepository linkRepository;

    public QueryResolver(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public List<Link> allLinks(LinkFilter filter, Number skip, Number first) {
        return linkRepository.getAllLinks(filter, skip.intValue(), first.intValue());
    }
}
