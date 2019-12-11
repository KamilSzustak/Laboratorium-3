import React from 'react';
import { Link, withRouter, Redirect } from 'react-router-dom';
import Post from "./Post";  
import { ListGroup, Button } from 'react-bootstrap';

class MainPage extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            posts: [],
            logout: localStorage.getItem("basicAuth") == null
        };

        this.handleLogoutButtonClick = this.handleLogoutButtonClick.bind(this);
    }

    componentDidMount() {
        fetch("http://localhost:8080/api/posts", {
            headers: {
                "Authorization": localStorage.getItem("basicAuth")
            }
        })
            .then(response => response.json())
            .then(data => {
                this.setState({
                    posts: data
                });
            })
    }

    handleLogoutButtonClick(event) {
        event.preventDefault();
        localStorage.removeItem("basicAuth");
        this.setState({
            logout: true
        });
        this.props.history.push("/");
    }

    render() {
        if (this.state.logout)
            return <Redirect to="/" />
            
        return(
            <div>
                <div>
                    <Button variant="outline-danger" type="button" style={{marginBottom: "16px"}} onClick={this.handleLogoutButtonClick}>Logout</Button>
                </div>
                <div>
                    <ListGroup>
                        {this.state.posts.map(post =>
                            <Link to={`/posts/${post.id}`} key={post.id}>
                                <ListGroup.Item key={post.id} style={{marginBottom: "16px"}}><Post data={post} /></ListGroup.Item>
                            </Link>
                        )}
                    </ListGroup>
                </div>
            </div>
        );
    }
}

export default withRouter(MainPage);