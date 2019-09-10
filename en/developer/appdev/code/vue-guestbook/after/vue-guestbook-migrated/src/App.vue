<template>
  <div id="app">
    <img alt="Vue logo" class="vue-app-logo" src="/o/vue-guestbook-migrated/logo.png">
    <router-view :entries="entries" :add="add"/>
  </div>
</template>

<script>
import ViewGuestbook from './components/ViewGuestbook.vue';
import AddEntry from './components/AddEntry.vue';
import api from './Api';

export default {
  name: 'app',
  components: {
    ViewGuestbook,
    AddEntry
  },
  data: function() {
    return {
      entries: [],
      entryId: 6
    }
  },
  created: function() {
      // api.getEntries().then(response => {
      //   this.loading = false;
      //   this.entries = response.data;
      // }, error => {
      //   this.loading = false;
      //   this.console.error(error);
      // });

      // TODO For mock data, replace method body with the line above. 
      this.entries = api.getEntries();
  },
  methods: {
    add: function (name, message) {
      if(name !== '' && message !== ''){
        this.entries.push({
          id: this.entryId++,
          name: name,
          message: message
        });
        
        this.$router.push({ path: '/view-guestbook' })
      }
      else {
        alert('you must fill in all fields');
      }
    }
  }
}
</script>

<style>
.test-class{
  background-color: red;
  width: 900px;
  height: auto;
}
</style>
