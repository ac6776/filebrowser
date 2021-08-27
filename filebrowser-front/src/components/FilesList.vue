<template>

  <button type="button" @click="increment">Click to change store</button>
  <button type="button" @click="step('E:\\Coding\\filebrowser')">Click to 2222</button>

  <div class="row align-items-start">
    <section v-if="data.loading">
      <div class="d-flex align-items-center">
        <strong>Loading...</strong>
        <div class="spinner-border ms-auto" role="status" aria-hidden="true"></div>
      </div>
    </section>
    <section v-if="data.errored">
      <div class="alert alert-danger mt-2 d-flex align-items-center" role="alert">
        <i v-if="data.errored.type === 'con'" class="bi bi-wifi-off icon"></i>
        <i v-else class="bi bi-exclamation-triangle-fill icon"></i>
        {{ data.errored.msg }}
      </div>
    </section>
    <div class="d-flex flex-wrap mt-2">
      <button
          v-if="data.parent"
          @click="step(data.parent.path)"
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
import datafetcher from "@/components/datafetcher";

export default {
  name: 'FilesList',
  props: {
    showHidden: Boolean,
    homeRequested: Boolean
  },
  data() {
    return {
      home: null,
      data: {
        home: null,
        parent: null,
        current: null,
        files: null,
        loading: true,
        errored: false
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
      this.data = response
    })

  },
  methods: {
    step: function (path) {
      let fetched = datafetcher.fetcher('post', path)
      fetched.then(response => {
        this.data = response
      })
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
      if(this.data.files) {
        return this.data.files.filter(f => {
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