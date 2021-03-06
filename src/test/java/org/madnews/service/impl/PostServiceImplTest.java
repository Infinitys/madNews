package org.madnews.service.impl;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.madnews.config.RootConfig;
import org.madnews.entity.Post;
import org.madnews.entity.Role;
import org.madnews.entity.Tag;
import org.madnews.entity.User;
import org.madnews.service.PostService;
import org.madnews.service.RoleService;
import org.madnews.service.TagService;
import org.madnews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashSet;
import java.util.Set;

@RunWith( SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@WebAppConfiguration
public class PostServiceImplTest extends TestCase {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private TagService tagService;
    @Autowired
    private RoleService roleService;

    @Test
    public void testCreateAndReadUser() throws Exception{
        Long roleid = Long.valueOf(1);
        Role role = roleService.readRole(roleid);
        User user = new User();
        user.setFirstname("first");
        user.setLastname("last");
        user.setEmail("test@mail.com");
        user.setPassword("123");
        user.setRole(role);
        userService.addUser(user);
        assertNotNull(userService.getUser(user.getId()));
    }

    @Test
    public void testCreateAndReadPost() throws Exception {
        User user = userService.getUser(Long.valueOf(0));
        Set<Tag> tags = new HashSet<>();
        tags.add(tagService.readTag(Long.valueOf(0)));
        tags.add(tagService.readTag(Long.valueOf(1)));
        ////////////////////
        Post post = new Post();
        post.setTitle("title");
        post.setContent("<html><body>Some Content</body></html>");
        post.setSmallImg("/image/id_small.jpeg");
        post.setBigImg("/image/id_big.jpeg");
        post.setRating(3);
        post.setIsTopNews(true);
        post.setUser(user);
        post.setTags(tags);
        post.setTimestamp((int) (System.currentTimeMillis() / 1000L));
        postService.createPost(post);
        Post postFromDB = postService.readPost(post.getId());
        assertEquals(postFromDB.getTitle().trim(), post.getTitle());
    }

    @Test
    public void testReadAndUpdatePost() throws Exception {
        Long id = Long.valueOf(1);
        String newTitle = "new title";
        Post post = postService.readPost(id); //retrieve post by id from DB
        assertNotNull(post);
        post.setTitle(newTitle);
        postService.updatePost(post);
        post = postService.readPost(id);
        assertEquals(newTitle, post.getTitle().trim());
    }

    @Test
    public void testCreateAndDeletePost() throws Exception{
        Post post = new Post();
        assertNotNull(post);
        Long postId = post.getId();
        postService.createPost(post);
        postService.deletePost(post);
        assertNull(postId);
    }
}