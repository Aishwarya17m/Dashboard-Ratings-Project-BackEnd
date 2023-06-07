package com.lti.movierating.services;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.movierating.entity.Movie;
import com.lti.movierating.entity.Ratings;
import com.lti.movierating.entity.User;
import com.lti.movierating.repository.RatingsRepository;
import com.lti.movierating.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RatingsRepository ratingsRepo;

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return userRepo.save(user);
	}

	@Override
	public Ratings addRatings(Ratings rating) {
		// TODO Auto-generated method stub
		return ratingsRepo.save(rating);
		
	}

	@Override
	public User getUserById(int id) {
		return userRepo.getReferenceById(id);
	}
	@Override
	public boolean checkIfAlreadyExists(int userId, int movieId) {
		// TODO Auto-generated method stub
		List<Ratings> ratings = ratingsRepo.findAll();

		for(int i=0;i<ratings.size();i++) {
			System.out.println(ratings.get(i).toString());
			if(ratings.get(i).getMovie().getMovieId()==movieId && ratings.get(i).getUser().getUserId()==userId) {
				System.out.println("Exists");
				return true;
			}
		}
		System.out.println("Doesn't exist");
		return false;
	}


	

	@Override
	public List<Ratings> getRatingsByUser(int id) {
		// TODO Auto-generated method stub
		List<Ratings> ratings = ratingsRepo.findAll();
		List<Ratings> ratedMovies = new ArrayList<Ratings>();
		
		for(int i=0;i<ratings.size();i++) {
			if(ratings.get(i).getUser().getUserId()==id) {
				ratedMovies.add(ratings.get(i));
			}
		}
		
		return ratedMovies;
	}
	
	@Override
	public List<Ratings> getRatingsByMovie(int id) {
		// TODO Auto-generated method stub
		List<Ratings> ratings = ratingsRepo.findAll();
		List<Ratings> ratedMovies = new ArrayList<Ratings>();
		
		for(int i=0;i<ratings.size();i++) {
			if(ratings.get(i).getMovie().getMovieId()==id) {
				ratedMovies.add(ratings.get(i));
			}
		}
		return ratedMovies;
	}
	
@Override
public float getMovieRating(int id) {
	// TODO Auto-generated method stub
	
	List<Ratings> ratings = ratingsRepo.findAll();
	float movieRatings=0;
	int total=0;
	
	for(int i=0;i<ratings.size();i++) {
		if(ratings.get(i).getMovie().getMovieId()==id) {
			movieRatings+=ratings.get(i).getRating();
			total+=1;
		}
		
	}
	
	return movieRatings/total;
}
}
