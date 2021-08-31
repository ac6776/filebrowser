<template>


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
          v-for="file in files"
          @click="step(file.path)"
          type="button" class="btn btn-light m-1" :disabled="!checkForDir(file)"
          :key="file.path">

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
import {useFetchingPosts} from "@/composables/useFetchingPosts";
import {useFilterHidden} from "@/composables/useFilterHidden";

export default {
  name: 'FilesList',
  props: {
    showHidden: Boolean,
    homeRequested: Boolean
  },
  setup() {
    const {parent, current, files, errored, loading, fetching} = useFetchingPosts()
    const {show, showHiddenFiles} = useFilterHidden(files)

    return {
      show,
      parent,
      current,
      files: showHiddenFiles,
      // files,
      // showHiddenFiles,
      errored,
      loading,
      fetching
    }
  },
  methods: {
    step: function (path) {
      console.log(path)
      this.fetching('post', path)
    },
    checkForDir(file) {
      return file.directory
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