import React, { useState } from 'react';
import './uploadProductScreen.css';
import axios from 'axios';

const UploadProductScreen = () => {
  const [productName, setProductName] = useState('');
  const [productDescription, setProductDescription] = useState('');
  const [productPrice, setProductPrice] = useState(0);
  const [productCond, setProductCond] = useState('');
  const [productVersion, setProductVersion] = useState(0.0);
  const [productImage, setProductImage] = useState('');
  const [username, setUsername] = useState('');

  const handleProductNameChange = (event) => {
    setProductName(event.target.value);
  };

  const handleProductCondChange = (event) => {
    setProductCond(event.target.value);
  };

const handleProductVersionChange = (event) => {
    setProductVersion(event.target.value);
  };
  const handleProductDescriptionChange = (event) => {
    setProductDescription(event.target.value);
  };

  const handleProductPriceChange = (event) => {
    setProductPrice(event.target.value);
  };

  const handleProductImageChange = (event) => {
    setProductImage(event.target.value);
  };

  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const handleUploadProduct = async () => {
    // create product object to send in JSON format
    const product = {
      username: username,
      
      cond: productCond,
      price: productPrice,
      picture: productImage,
       
      isbn: productDescription,
      title: productName,
     
      version: productVersion,
      
    };

    // make API call to upload product data to server
    try {
      const response = await axios.post('http://localhost:8080/api/inventory/add', product)
      console.log('Product uploaded successfully:', response.data);
    } catch (error) {
      console.error('Error uploading product:', error.message);
    }
  };

  return (
    <div className="upload-product-screen">
      <h1>Upload Product</h1>
      <form>
        <label htmlFor="productName">Product Name:</label>
        <input
          type="text"
          id="productName"
          name="productName"
          value={productName}
          onChange={handleProductNameChange}
          
        />
        <br />
        <label htmlFor="productCond">Product Condition:</label>
        <textarea
          id="productCond"
          name="productCond"
          value={productCond}
          onChange={handleProductCondChange}
        ></textarea>
        <br />
        <label htmlFor="productPrice">Product Price:</label>
        <input
          type="number"
          id="productPrice"
          name="productPrice"
          value={productPrice}
          onChange={handleProductPriceChange}
        />
        <br />
        <label htmlFor="productImage">Product Image:</label>
        <input
          type="text"
          id="productImage"
          name="productImage"
          onChange={handleProductImageChange}
        />
        <br />
        <label htmlFor="productDescription">Product ISBN:</label>
        <input
          type="text"
          id="productDescription"
          name="productDescription"
          onChange={handleProductDescriptionChange}
        />
        <label htmlFor="productVersion">Product Version:</label>
        <input
          type="text"
          id="productVersion"
          name="producVersion"
          onChange={handleProductVersionChange}
        />
        <label htmlFor="username">Username:</label>
        <input
          type="text"
          id="username"
          name="username"
          onChange={handleUsernameChange}
        />
        <br />
        <button
          type="button"
          className="yellow-button"
          onClick={handleUploadProduct}
        >
          Upload Product
        </button>
      </form>
    </div>
  );
};

export default UploadProductScreen;
