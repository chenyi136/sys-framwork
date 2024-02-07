package com.enterprise.base.system.post.model;

import com.enterprise.base.system.post.db.SysPostEntity;
import com.enterprise.base.system.post.db.SysPostService;
import com.enterprise.common.exception.ApiException;
import com.enterprise.common.exception.error.ErrorCode.Business;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author valarchie
 */
@Component
@RequiredArgsConstructor
public class PostModelFactory {

    private final SysPostService postService;

    public PostModel loadById(Long postId) {
        SysPostEntity byId = postService.getById(postId);
        if (byId == null) {
            throw new ApiException(Business.COMMON_OBJECT_NOT_FOUND, postId, "职位");
        }
        return new PostModel(byId, postService);
    }

    public PostModel create() {
        return new PostModel(postService);
    }

}
