import React from 'react';
import { Link } from 'react-router-dom';

class EntryForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      name: '',
      message: ''
    };
  }

  handleChange(event) {
    this.setState({[event.target.name]: event.target.value});
  }
  
  handleClick(event) {
    if(this.state.name !== '' && this.state.message !== ''){
      this.props.addMethod(this.state.name, this.state.message);
    }
    else {
      alert('you must fill in all fields');
    }
  }
  
  render() {

      return (
        <form>
          <div className="form-group">
            <label htmlFor="addEntryNameInput">Name</label>
            <input className="form-control" id="addEntryNameInput" value={this.state.name} onChange={this.handleChange.bind(this)} name="name" aria-label="name" />
          </div>
          <div className="form-group mb-4">
            <label htmlFor="addEntryMessageInput">Message</label>
            <input className="form-control" id="addEntryMessageInput" value={this.state.message} onChange={this.handleChange.bind(this)} name="message" aria-label="message" />
          </div>
          <button type="button" className="btn btn-primary mr-2" onClick={this.handleClick.bind(this)}>Save</button>
          <Link to="/view-guestbook" className="btn btn-outline-secondary">Cancel</Link>
        </form>
      );
  }
}

export default EntryForm;