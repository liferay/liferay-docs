<template>
  <div class="ml-2 mr-2">
    <form>
      <fieldset>
        <div class="form-group">
          <label for="addEntryNameInput">Name</label>
          <input class="form-control" id="addEntryNameInput" name="name" v-model="name" aria-label="name" />
        </div>
        <div class="form-group mb-4">
          <label for="addEntryMessageInput">Message</label>
          <input class="form-control" id="addEntryMessageInput" name="message" v-model="message" aria-label="message" />
        </div>
      </fieldset>

      <div class="button-footer">  
        <button type="button" class="btn btn-primary mr-2" v-on:click="add(name, message)">Save</button>
        <routerLink to="/view-guestbook" class="btn btn-outline-secondary">Cancel</routerLink>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: 'AddEntry',
  data: function() {
    return {
    name: '',
    message: '' 
    }
  },
  props: ['add']
}
</script>