package com.scm.scm.projection;

import org.springframework.data.rest.core.config.Projection;

@Projection(types = {com.scm.scm.entity.User.class}, name = "userProjection")
public interface UserProjection {

    String getName();
    String getEmail();
    String getPhonenumber();
    
} 
