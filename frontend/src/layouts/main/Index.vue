<template>
  <v-app>
    <AppHeader />

    <Navbar />

    <MainView />
    
  </v-app>
</template>

<script>
import AppHeader from '@/components/common/AppHeader.vue'
import Navbar from '@/components/common/Navbar.vue'
import MainView from '@/layouts/main/View.vue'

export default {
  name: 'MainLayout',
  components: {
    AppHeader,
    Navbar,
    MainView,
  }
}
</script>

<style>

</style>