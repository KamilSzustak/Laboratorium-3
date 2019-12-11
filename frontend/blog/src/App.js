import React from 'react';
import './App.css';
import { BrowserRouter, Switch, Route, Link, Redirect } from 'react-router-dom';    
import PostPage from './PostPage';
import MainPage from './MainPage';
import NewPostPage from './NewPostPage';
import EditPostPage from './EditPostPage';
import Button from 'react-bootstrap/Button';
import AuthorizationRoute from './AuthorizationRoute';
import LoginPage from "./LoginPage";
import SignUpPage from "./SignUpPage";

class App extends React.Component {

    render() {
        return (
            <div>
                <BrowserRouter>
                    <div style={{marginBottom: "16px"}}>
                        <Link to="/">
                            <h1 style={{display: "inline"}}>Blog</h1>
                        </Link>
                        <Link to="/new">
                            <Button variant="primary" style={{marginLeft: "16px"}}>New post</Button>
                        </Link>
                    </div>
                    <div>
                        <Switch>
                            <Route exact path="/" component={AuthorizationRoute} />
                            <Route path="/login" component={LoginPage} />
                            <Route path="/signup" component={SignUpPage} />
                            <Route path="/home" component={MainPage} />
                            <Route path="/posts/:id" component={PostPage} />
                            <Route path="/new" component={NewPostPage} />
                            <Route path="/edit/:id" component={EditPostPage} />
                        </Switch>
                    </div>
                </BrowserRouter>
            </div>
        );
    }
}

export default App;
