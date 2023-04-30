import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const ReviewForm = ({ username }) => {
  const history = useNavigate();
  
    const [reviewUsername, setReviewUsername] = useState('');
    const [reviewService, setReviewService] = useState('');
    const [reviewRating, setReviewRating] = useState(0);
    const [reviewText, setReviewText] = useState('');
  
    const handleSubmit = async (event) => {
      event.preventDefault();
      // submit review
      await submitReview(reviewUsername, reviewService, reviewRating, reviewText);
      // clear form inputs
      setReviewUsername('');
      setReviewService('');
      setReviewRating(0);
      setReviewText('');
      // redirect to home page
      history.push('/');
    };
  
    return (
      <form onSubmit={handleSubmit}>
        <label htmlFor="reviewUsername">Username of person being reviewed:</label>
        <input type="text" id="reviewUsername" name="reviewUsername" value={reviewUsername} onChange={(e) => setReviewUsername(e.target.value)} required />
        <br />
        <label htmlFor="reviewService">Reviewing as:</label>
        <select id="reviewService" name="reviewService" value={reviewService} onChange={(e) => setReviewService(e.target.value)} required>
          <option value="buyer">Buyer</option>
          <option value="seller">Seller</option>
        </select>
        <br />
        <label htmlFor="reviewRating">Rating:</label>
        <input type="number" id="reviewRating" name="reviewRating" value={reviewRating} min="0" max="5" step="0.1" onChange={(e) => setReviewRating(parseFloat(e.target.value))} required />
        <br />
        <label htmlFor="reviewText">Review:</label>
        <textarea id="reviewText" name="reviewText" value={reviewText} onChange={(e) => setReviewText(e.target.value)} required />
        <br />
        <button type="submit">Submit Review</button>
      </form>
    );
  };
  
  const submitReview = async (reviewUsername, reviewService, reviewRating, reviewText) => {
    const url = `http://localhost:8080/api/review/add/${reviewUsername}`;
    const data = { username: reviewUsername, service: reviewService, rating: reviewRating, review: reviewText };
    try {
      const response = await fetch(url, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data),
      });
      if (!response.ok) {
        throw new Error('Failed to submit review');
      }
    } catch (error) {
      console.error(error);
      // handle error
    }
  };
  
  export default ReviewForm;