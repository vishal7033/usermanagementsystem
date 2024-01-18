package com.mybatis.springbootmybatis2.controller;
import com.mybatis.springbootmybatis2.mapper.Usermapper;
import com.mybatis.springbootmybatis2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class Usercontroller {

    @Autowired
    private Usermapper usermapper;

    @GetMapping("/all")
    public List<User> getAllUsersWithPuja()
    {
        return usermapper.getAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id)
    {
        return usermapper.getUserById(id);
    }


    @PostMapping("/add")
    public ResponseEntity<Integer> addUser(@RequestBody(required = false) User user) {
        if (user == null) {
            return ResponseEntity.badRequest().body(-1);
        }

        try {
            int result = usermapper.insertUser(user);
            HttpStatus status = (result > 0) ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(status).body(result > 0 ? result : -1);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(-1);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody(required = false) User user) {
        if (id == null || user == null)
        {
            return ResponseEntity.badRequest().body("Invalid request. Please provide user ID and data.");
        }
        user.setId(id);
        try {
            int result = usermapper.updateUser(user);

            HttpStatus status = (result > 0) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
            String message = (result > 0) ? "User updated successfully" : "User not found or failed to update";

            return ResponseEntity.status(status).body(message);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User data integrity violation");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user due to a database error");
        }
    }


    @DeleteMapping("/delete/{id}")
    public int deleteUser(@PathVariable Integer id) {
        return usermapper.deleteUser(id);
    }
}
