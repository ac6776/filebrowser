<template>
  <div class="container mt-5">
    <div class="d-flex justify-content-between">
      <h1 class="primary-font">My filebrowser</h1>
      <NavBar
          @showHiddenFromBar="show($event)"
          @goHome="step($event)"
          @go-back="undo($event)"
          @go-forward="redo($event)"

          :undo-history="undoHistory"
          :redo-history="redoHistory"
      />
    </div>

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
      undoHistory: [],
      redoHistory: []
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
    step(node) {
      this.undoHistory.push(this.current)
      this.redoHistory = []
      this.fetching('post', node.path)
    },
    redo() {
      if (this.redoHistory.length !== 0) {
        const node = this.redoHistory.pop()
        this.undoHistory.push(this.current)
        this.fetching('post', node.path)
      }
    },
    undo() {
      if (this.undoHistory.length !== 0) {
        this.redoHistory.push(this.current)
        const node = this.undoHistory.pop()
        this.fetching('post', node.path)
      }
    }
  },
  mounted() {
    this.fetching('get')
  }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap');
.primary-font {
  font-family: 'Bebas Neue', cursive;
}
</style>