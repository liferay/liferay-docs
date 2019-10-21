import React from 'react';
import { Link } from 'react-router-dom';

class Guestbook extends React.Component {

  render() {
    
    return (
      <div className="guestbook">
        <h1>Guestbook</h1>
        <table className="table table-striped table-bordered mb-4">
          <thead className="thead-light">
            <tr>
              <th>Name</th>
              <th>Message</th>
            </tr>
          </thead>
          <CreateTableData entryData={this.props.entries}/>
        </table>
        <div>
          <Link to="/add-entry" onClick={this.props.resetRedirect} className="btn btn-outline-secondary">Add Entry</Link>
        </div>
      </div>
    );
  }
}
const CreateTableData = props => {

    const entries = props.entryData.map((row, index) => {
          
      return (
        <tr key={index}>
          <td>{row.name}</td>
          <td>{row.message}</td>
        </tr>
      )
    });
    
    return <tbody>{entries}</tbody>;

}


export default Guestbook;