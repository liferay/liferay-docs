<template>
  <div class="ml-2 mr-2">
    <h1>Guestbook</h1>
    <table class="table table-striped table-bordered mb-4">
      <thead class="thead-light">
        <tr>
          <th>Name</th>
          <th>Message</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="entry in entries" :key="entry.id">
          <td>{{entry.name}}</td>
          <td>{{entry.message}}</td>
        </tr>
      </tbody>
    </table>
    <div class="button-footer">
      <routerLink to="/add-entry" class="btn btn-outline-secondary">Add Entry</routerLink>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ViewGuestbook',
  props: ['entries']
}
</script>