<template>
  <div class="container mt-5">
    <div class="d-flex justify-content-between">
      <h1 class="primary-font">My filebrowser</h1>
      <NavBar
          @showHiddenFromBar="show($event)"
          @goHome="step($event)"/>
    </div>

    <button type="button" @click="show">ShowHidden</button>
    <FilesList
      :parent="this.parent"
      :current="this.current"
      :files="this.files"
      :loading="this.loading"
      :errored="this.errored"

      @step="step($event)"/>
  </div>
</template>

<script>
import FilesList from "@/components/FilesList";
import NavBar from "@/components/NavBar";
import {ref} from "vue";
import {useFetchingFiles} from "@/composables/useFetchingFiles";
import {useFilterHidden} from "@/composables/useFilterHidden";

export default {
  name: 'App',
  data() {
    return {
    }
  },
  setup() {
    const showHidden = ref(false)
    const {parent, current, files, errored, loading, fetching} = useFetchingFiles()
    const {showHiddenFiles} = useFilterHidden(showHidden, files)

    return {
      showHidden,
      parent,
      current,
      files: showHiddenFiles,
      errored,
      loading,
      fetching
    }
  },
  components: {
    FilesList,
    NavBar
  },
  methods: {
    show() {
      this.showHidden = !this.showHidden
    },
    step(path) {
      this.fetching('post', path)
    }
  }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap');
.primary-font {
  font-family: 'Bebas Neue', cursive;
}
</style>
