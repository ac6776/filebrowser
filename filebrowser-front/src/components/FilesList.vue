<template>
  <div class="row align-items-start">
    <div class="col">
      <section v-if="loading">
        <h2>Loading</h2>
      </section>
      <section v-if="errored">
        <h2>Unable to load</h2>
      </section>
      <div class="list-group mt-2">
<!--        <button type="button" class="list-group-item list-group-item-action active" aria-current="true">-->
<!--          The current button-->
<!--        </button>-->
        <button v-for="file in files" v-on:click="step(file.path)" type="button" class="list-group-item list-group-item-action">{{ file.name }}</button>
      </div>
    </div>
    <div class="col">
    </div>
    <div class="col">
    </div>
  </div>
</template>

<script>
const axios = require('axios').default;

export default {
  name: 'FilesList',
  data() {
    return {
      parent: null,
      files: null,
      backUrl: null,
      errored: false,
      loading: true
    };
  },
  mounted() {
    axios
        .get('http://localhost:8080/')
        .then(response => (
            this.parent = response.data.parentPath,
                this.files = response.data.fileObjectList
        ))
    .catch(error => {
      console.log(error);
      this.errored = true;
    })
    .finally(() => (
        this.loading = false
    ));
  },
  methods: {
    step: function (path) {
      axios
          .post('http://localhost:8080/', {
            path
          })
      .then(response => (
          this.parent = response.data.parentPath,
          this.files = response.data.fileObjectList
      ));
    },
  }
}
</script>