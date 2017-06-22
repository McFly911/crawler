package com.mcfly911.mcraw.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements IService {

	@Autowired private Repo1 repo1;
	@Autowired private Repo2 repo2;
	@Autowired private Repo3 repo3;
	@Autowired private Repo4 repo4;
	@Autowired private Repo5 repo5;
	@Autowired private Repo6 repo6;

	@Override
	public Repo1 getRepo1() {
		return repo1;
	}
	@Override
	public Repo2 getRepo2() {
		return repo2;
	}
	@Override
	public Repo3 getRepo3() {
		return repo3;
	}
	@Override
	public Repo4 getRepo4() {
		return repo4;
	}
	@Override
	public Repo5 getRepo5() {
		return repo5;
	}
	@Override
	public Repo6 getRepo6() {
		return repo6;
	}
}
