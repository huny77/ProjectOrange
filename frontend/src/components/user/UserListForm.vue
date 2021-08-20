<template>
  <v-container>
    <v-row justify="space-between">
      <v-col class="text-h5">{{ username }}</v-col>
      <v-col class="mb-5">
        <v-btn outlined text color='red'>삭제</v-btn> 
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'UserListForm',
  data() {
    return {
      username: '',
    }
  }
}
</script>

<style>

</style>