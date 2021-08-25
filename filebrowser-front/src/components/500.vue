<template>
  <!-- Button trigger modal -->
<!--  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#error500Modal">-->
  <button type="button" class="btn btn-primary" @click="showModal()">
    Launch demo modal
  </button>

  <!-- Modal -->
  <div class="modal fade" id="error500Modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          This is a modal
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import bootstrap from 'bootstrap/dist/js/bootstrap.bundle.min'

export default {
  name: "500",
  props: {
    errored: Boolean
  },
  data() {
    return {
      // errorModal: document.getElementById('error500Modal')
      myModal: null
    }
  },
  methods: {
    showModal() {
      let myModal = new bootstrap.Modal(document.getElementById('error500Modal'), {
        backdrop: true,
        focus: true
      });
      // var myInput = document.getElementById('myInput');

      myModal.show();
    }
  },
  watch: {
    errored(newVal, oldVal) {
      this.showModal()
    }
  },
  mounted() {

  }
}
</script>

<style scoped>

</style>