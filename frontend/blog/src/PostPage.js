import React from 'react';
import { withRouter, Redirect, Link } from 'react-router-dom';
import CommentForm from './CommentForm';
import { ButtonToolbar, Button, ListGroup } from 'react-bootstrap';

class PostPage extends React.Component {
    
    constructor(props) {
        super(props);

        this.state = {
            id: props.match.params.id,
            post: {
                title: "",
                content: "",
                comments: []
            },
            done: false
        };

        this.handleDeleteButtonClick = this.handleDeleteButtonClick.bind(this);
    }

    componentDidMount() {
        fetch(`http://localhost:8080/api/posts/${this.state.id}`)
            .then(response => response.json())
            .then(data => {
                this.setState({
                    post: data
                });
            })
    }

    handleDeleteButtonClick() {
        const deletePost = window.confirm("Do you want to delete this post?");
        if (deletePost) {
            fetch(`http://localhost:8080/api/posts/${this.state.id}`, {
                method: "DELETE"
            })
                .then(() => {
                    this.setState({
                        done: true
                    });
                });
        }
    }

    render() {
        if (this.state.done)
            return <Redirect to="/" />

        return (
            <div>
                <h1 style={{display: "inline"}}>{this.state.post.title}</h1>
                <ButtonToolbar style={{display: "inline", marginLeft: "16px"}}>
                    <Button variant="danger" size="sm" onClick={this.handleDeleteButtonClick}>Delete</Button>
                    {" "}
                    <Link to={`/edit/${this.state.id}`}>
                        <Button variant="info" size="sm">Edit</Button>
                    </Link>
                </ButtonToolbar>
                <p>{this.state.post.content}</p>
                <h4>Comments</h4>
                <CommentForm data={this.state.id} />
                <ListGroup>
                    {this.state.post.comments.map(comment => <ListGroup.Item key={comment.id} style={{marginTop: "16px"}}>{comment.content}</ListGroup.Item>)}
                </ListGroup>
            </div>
        );
    }
}

export default withRouter(PostPage);