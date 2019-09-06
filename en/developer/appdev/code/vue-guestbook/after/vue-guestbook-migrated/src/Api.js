// import axios from 'axios';

export default class Api {
    static getEntries() {
        // This is a mock web service call
        // return axios.get(`https://localhost:8080/o/api/p/gb/entry`);

        // This returns mock data
        return [
          { id: 1, name: 'Joe Bloggs', message: 'Had an awesome Time!' },
          { id: 2, name: 'Jane Bloggs', message: 'Great event!' },
          { id: 3, name: 'Bill Bloggs', message: 'Had a good time.' },
          { id: 4, name: 'Bob Nosester', message: 'Great atmosphere!' },
          { id: 5, name: 'Martha Nosester', message: 'Lovely aromas.' }
        ]

        // Here's a service to try too
        // return axios.get(`https://jsonplaceholder.typicode.com/users`);
    }

    static addEntry(entry) {
        return fetch('http://localhost:8080/o/api/p/gb/entry', {
            method: 'POST',
            body: JSON.stringify(entry),
            headers: {
              "Content-type": "application/json; charset=UTF-8"
            }
          })
          .then(response => response.json())
          .then(json => this.console.log(json))
    }
}
