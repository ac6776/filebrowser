<template>
  <div class="container mt-5">
    <h1>My filebrowser</h1>
    <div class="mt-2">
      <button type="button" class="btn btn-outline-primary m-1">Показать скрытые файлы</button>
      <button type="button" class="btn btn-outline-secondary m-1">Спрятать скрытые файлы</button>
      <button type="button" class="btn btn-outline-success m-1">Показать скрытые папки</button>
      <button type="button" class="btn btn-outline-danger m-1">Спрятать Скрытые файлы</button>
    </div>
    <div>
      <FilesList></FilesList>
    </div>
  </div>
</template>

<script>
import FilesList from "@/components/FilesList";

export default {
  name: 'App',
  components: {
    FilesList
  }
}
</script>

<style lang="less">

</style>
