import { BrowserRouter, Navigate, Link, Route, Routes } from 'react-router-dom'
import { ToastContainer } from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css'
import HomeScreen from './screens/HomeScreen'
import ProductScreen from './screens/ProductScreen'
import Navbar from 'react-bootstrap/Navbar'
import Badge from 'react-bootstrap/Badge'
import Nav from 'react-bootstrap/Nav'
import NavDropdown from 'react-bootstrap/NavDropdown'
import Container from 'react-bootstrap/Container'
import { LinkContainer } from 'react-router-bootstrap'
import { useContext, useState } from 'react'
import { Store } from './Store'
import CartScreen from './screens/CartScreen'
import SigninScreen from './screens/SigninScreen'
import ShippingAddressScreen from './screens/ShippingAddressScreen'
import SignupScreen from './screens/SignupScreen'
import PaymentMethodScreen from './screens/PaymentMethodScreen'
import PlaceOrderScreen from './screens/PlaceOrderScreen'
import OrderScreen from './screens/OrderScreen'
import OrderHistoryScreen from './screens/OrderHistoryScreen'
import ProfileScreen from './screens/ProfileScreen'
import MessageScreen from './screens/MessageScreen'
import uploadProductScreen from './screens/uploadProductScreen'

import './App.css'
import UploadProductScreen from './screens/uploadProductScreen'
import ReviewForm from './screens/ReviewForm'


function App() {
  
  const { state, dispatch: ctxDispatch } = useContext(Store)
  const { cart, userInfo } = state
  const [searchTerm, setSearchTerm] = useState('')
  const [notifications, setNotifications] = useState(0)
  const signoutHandler = () => {
    ctxDispatch({ type: 'USER_SIGNOUT' })
    localStorage.removeItem('userInfo')
    localStorage.removeItem('shippingAddress')
    localStorage.removeItem('paymentMethod')
    window.location.href = '/signin'
  }
  const handleSearchChange = (event) => {
    setSearchTerm(event.target.value)
  }

  const handleSearchSubmit = (event) => {
    event.preventDefault();
    if (searchTerm.trim() !== "") {
      // Make a request to the server to search for the product
      fetch(`http://localhost:8080/api/inventory/search/Math`)
        .then((response) => response.json())
        .then((data) => {
          // If a match is found, redirect to the product page
          if (data.length > 0) {
            const productId = data[0].id;
            window.location.href = `/products/${productId}`;
          }
        })
        .catch((error) => {
          console.error(error);
        });
    }
  };

  return (
    <BrowserRouter>
      <div className="d-flex flex-column site-container">
        <ToastContainer position="bottom-center" limit={1} />
        <header>
          <Navbar bg="dark" variant="dark" expand="lg">
            <Container>
              <LinkContainer to="/">
                <Navbar.Brand>BookSharing</Navbar.Brand>
              </LinkContainer>
              <form onSubmit={handleSearchSubmit}>
                <div className="search-bar" id="mydiv">
                  <input
                    type="text"
                    placeholder="Search..."
                    value={searchTerm}
                    onChange={handleSearchChange}
                  />
                  <button type="submit">Go</button>
                </div>
              </form>
            
        
        <Routes>
          <Route exact path="/" component={HomeScreen} />
          <Route exact path="/products/:id" component={ProductScreen} />
          <Route to="/products" />
        </Routes>
    

              <Navbar.Toggle aria-controls="basic-navbar-nav" />
              <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="me-auto  w-100  justify-content-end">
                  <Link to="/cart" className="nav-link">
                    Cart
                    {cart.cartItems.length > 0 && (
                      <Badge pill bg="danger">
                        {cart.cartItems.reduce((a, c) => a + c.quantity, 0)}
                      </Badge>
                    )}
                  </Link>

                  {userInfo ? (
                    <NavDropdown title={userInfo.name} id="basic-nav-dropdown">
                      <LinkContainer to="/profile">
                        <NavDropdown.Item>User Profile</NavDropdown.Item>
                      </LinkContainer>
                      <LinkContainer to="/orderhistory">
                        <NavDropdown.Item>Order History</NavDropdown.Item>
                      </LinkContainer>
                      <NavDropdown.Divider />
                      <Link
                        className="dropdown-item"
                        to="#signout"
                        onClick={signoutHandler}
                      >
                        Sign Out
                      </Link>
                    </NavDropdown>
                  ) : (
                    <Link className="nav-link" to="/signin">
                      Sign In
                    </Link>
                  )}

                  <Link
                    className="nav-link"
                    id="message-icon_notifications"
                    to="/review"
                  >
                    Review
                    
                  </Link>
                  {notifications > 0 && (
                    <span id="message-icon__notifications">
                      {notifications}
                    </span>
                  )}
                  <Link
                    className="nav-link"
                    id="message-icon_notifications"
                    to="/upload"
                  >
                    UploadProduct
                    
                  </Link>
                  {/* {notifications > 0 && (
                    <span id="message-icon__notifications">
                      {notifications}
                    </span>
                  )}
                  <Link className="nav-link" id="profile" to="/profile">
                    profile
                  </Link> */}
                </Nav>
              </Navbar.Collapse>
            </Container>
          </Navbar>
        </header>
        <main>
          <Container className="mt-3">
            <Routes>
              <Route path="/product/:slug" element={<ProductScreen />} />
              <Route path="/cart" element={<CartScreen />} />
              <Route path="/signin" element={<SigninScreen />} />
              <Route path="/signup" element={<SignupScreen />} />
              <Route path="/profile" element={<ProfileScreen />} />
              <Route path="/placeorder" element={<PlaceOrderScreen />} />
              <Route path="/order/:id" element={<OrderScreen />}></Route>
              <Route path="/review" element={<ReviewForm/>}></Route>
              <Route path="/message" element={<MessageScreen/>}></Route>
              <Route
                path="/orderhistory"
                element={<OrderHistoryScreen />}
              ></Route>
              <Route
                path="/shipping"
                element={<ShippingAddressScreen />}
              ></Route>
              <Route path="/payment" element={<PaymentMethodScreen />}></Route>
              <Route path="/" element={<HomeScreen />} />
              <Route path="/upload" element={<UploadProductScreen/>}></Route>
            </Routes>
            
          </Container>
        </main>
        <footer>
          <div className="text-center">All rights reserved</div>
        </footer>
      </div>
    </BrowserRouter>
  )
}

export default App
