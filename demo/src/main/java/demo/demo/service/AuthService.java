package demo.demo.service;

import demo.demo.entity.Member;

public interface AuthService {
    void register(Member userToAdd);
    String login(int username, String password);
    String refresh(String oldToken);
}