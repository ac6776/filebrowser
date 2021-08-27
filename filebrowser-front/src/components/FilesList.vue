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
          v-if="info.parent"
          @click="step(info.parent.path)"
          type="button" class="btn btn-light m-1"><i class="bi bi-arrow-90deg-up go-back-arrow"></i>..</button>

      <button
          v-for="file in filter()"
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
import datafetcher from "@/components/datafetcher";
import NavBar from "@/components/NavBar";

export default {
  name: 'FilesList',
  components: {
    NavBar
  },
  props: {
    showHidden: Boolean,
    homeRequested: Boolean
  },
  data() {
    return {
      home: null,
      loading: true,
      errored: false,
      info: {
        home: null,
        parent: null,
        current: null,
        files: null,
      }
    };
  },
  watch: {
    homeRequested(newVal, oldVal) {
      this.step(this.home.path)
    }
  },
  mounted() {
    let fetched = datafetcher.fetcher('get')
    fetched.then(response => {
      this.info = response
      if (!this.home) {
        this.home = response.home
      }
    }).catch(error => {
      this.errored = datafetcher.getError(error)
    }).finally(() => {
      this.loading = false
    })

  },
  methods: {
    step: function (path) {
      this.errored = false;
      this.loading = true;

      console.log(path);
      let fetched = datafetcher.fetcher('post', path)
      fetched.then(response => {
        console.log(response)
        this.info = response
      }).catch(error => {
        this.errored = datafetcher.getError(error)
      }).finally(() => {
        this.loading = false
      })
    },
    checkForDir(file) {
      return file.directory
    },
    increment() {
      this.$store.commit('increment')
      console.log(this.$store.state.count)
    },
    filter() {
      if(this.info.files) {
        return this.info.files.filter(f => {
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