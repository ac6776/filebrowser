<template>
  <div class="row align-items-start">
    <section v-if="loading">
      <div class="d-flex align-items-center">
        <strong>Loading...</strong>
        <div class="spinner-border ms-auto" role="status" aria-hidden="true"></div>
      </div>
    </section>
    <section v-if="errored">
      <div class="alert alert-danger mt-2" role="alert">
        Unable to load
      </div>
    </section>
    <div class="col">
      <div  class=""></div>
      <div class="list-group mt-2 text-break">
        <button v-if="parent" @click="step(parent.path)" type="button" class="list-group-item list-group-item-action"><i class="bi bi-arrow-90deg-up go-back-arrow"></i>..</button>
        <button v-for="file in showFiles" v-on:click="step(file.path)" type="button" class="list-group-item list-group-item-action">{{ file.name }}</button>
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
  props: {
    showHidden: Boolean
  },
  data() {
    return {
      parent: null,
      current: null,
      files: null,
      errored: false,
      loading: true
    };
  },
  mounted() {
    axios
      .get('http://localhost:8080/')
      .then(response => (
        this.parent = response.data.parent,
        this.current = response.data.current,
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
          this.parent = response.data.parent,
          this.current = response.data.current,
          this.files = response.data.fileObjectList
        ));
    },
  },
  computed: {
    showFiles: function() {
      if(this.files) {
        return this.files.filter(f => {
          if (this.showHidden) {
            return f;
          } else {
            if (!f.hidden) {
              return f;
            }
          }
        })
      }
    }
  }
}
</script>
<style>
  .go-back-arrow {
    font-size: .85rem;
    margin-right: 1rem;
  }
</style>