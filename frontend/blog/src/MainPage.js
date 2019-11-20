import React from 'react';
import { Link, withRouter } from 'react-router-dom';
import Post from "./Post";  
import { ListGroup } from 'react-bootstrap';

class MainPage extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            posts: []
        };
    }

    componentDidMount() {
        fetch("http://localhost:8080/api/posts")
            .then(response => response.json())
            .then(data => {
                this.setState({
                    posts: data
                });
            })
    }

    render() {
        return (
            <div>
                <ListGroup>
                    {this.state.posts.map(post =>
                        <Link to={`/posts/${post.id}`} key={post.id}>
                            <ListGroup.Item key={post.id} style={{marginBottom: "16px"}}><Post data={post} /></ListGroup.Item>
                        </Link>
                    )}
                </ListGroup>
            </div>
        );
    }
}

export default withRouter(MainPage);