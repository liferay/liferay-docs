import React, { Component } from 'react';
import { Route, Switch, Redirect } from 'react-router-dom';
import EntryForm from './add-entry';
import Guestbook from './view-guestbook';

class App extends Component {
  
  state = {
    entries: [
      { id: 1, name: 'Joe Bloggs', message: 'Had an awesome Time!' },
      { id: 2, name: 'Jane Bloggs', message: 'Great event!' },
      { id: 3, name: 'Bill Bloggs', message: 'Had a good time.' },
      { id: 4, name: 'Bob Nosester', message: 'Great atmosphere!' },
      { id: 5, name: 'Martha Nosester', message: 'Lovely aromas.' }
    ],
    redirect: false
  };
  
  addEntry = (name, message) => {
    const {entries} = this.state;
    
    const newEntry = {id: this.state.entries.length + 1, name: name, message: message};
    
    if(name !== '' && message !== ''){
      this.setState({
        entries: entries.concat(newEntry),
        redirect: true
      });
    }
  }
  
  resetRedirect = () => {
    this.setState({
      redirect: false
    });
  }
  
  render() {
    return (
      <Switch>
        <Route exact path="/" render={() => (<Redirect to="/view-guestbook"/>)}/>
        <Route path="/view-guestbook" render={() => < Guestbook entries={this.state.entries} resetRedirect={this.resetRedirect}/>}/>
        <Route path="/add-entry" render={() => this.state.redirect ? (< Redirect to="/view-guestbook"/>) : (< EntryForm addMethod={this.addEntry}/>)}/>
      </Switch>
    )
  }
}

export default App;
