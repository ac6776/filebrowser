<template>

  <button type="button" @click="increment">Click to change store</button>

  <div class="row align-items-start">
    <section v-if="loading">
      <div class="d-flex align-items-center">
        <strong>Loading...</strong>
        <div class="spinner-border ms-auto" role="status" aria-hidden="true"></div>
      </div>
    </section>
    <section v-if="errored">
      <div class="alert alert-danger mt-2 d-flex align-items-center" role="alert">
        <i v-if="errored.type === 'con'" class="bi bi-wifi-off icon"></i>
        <i v-else class="bi bi-exclamation-triangle-fill icon"></i>
        {{ errored.msg }}
      </div>
    </section>
    <div class="d-flex flex-wrap mt-2">
      <button
          v-if="parent"
          @click="step(parent.path)"
          type="button" class="btn btn-light m-1"><i class="bi bi-arrow-90deg-up go-back-arrow"></i>..</button>
      <button
          v-for="file in showFiles"
          @click="step(file.path)"
          type="button" class="btn btn-light m-1" :disabled="!checkForDir(file)">

        <span v-if="checkForDir(file)">
          <i class="bi bi-folder"></i>
        </span>
        <span v-else>
          <i class="bi bi-file-earmark"></i>
        </span>

        {{ file.name }}
      </button>
    </div>
  </div>
</template>

<script>
const axios = require('axios').default;

export default {
  name: 'FilesList',
  props: {
    showHidden: Boolean,
    homeRequested: Boolean
  },
  data() {
    return {
      parent: null,
      current: null,
      files: null,
      loading: true,
      errored: false
    };
  },
  watch: {
    homeRequested(newVal, oldVal) {
      this.step(this.home.path)
    }
  },
  mounted() {
    axios
      .get('http://localhost:8080/')
      .then(response => (
        // this.home = response.data.current,
        this.$store.state.home = response.data.current,
        this.parent = response.data.parent,
        this.current = response.data.current,
        this.files = response.data.fileObjectList
      ))
      .catch(error => {
        this.errored = getError(error)
      })
      .finally(() => (
        this.loading = false
      ));
  },
  methods: {
    step: function (path) {
      this.errored = false,
      this.loading = true,

      axios
        .post('http://localhost:8080/', {
          path
          // path: '222'
        })
        .then(response => (
          this.parent = response.data.parent,
          this.current = response.data.current,
          this.files = response.data.fileObjectList
        ))
        .catch((error) => {
          this.errored = getError(error)
        })
        .finally(() => {
          this.loading = false;
        });
    },
    checkForDir(file) {
      return file.directory
    },
    increment() {
      this.$store.commit('increment')
      console.log(this.$store.state.count)
    }
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