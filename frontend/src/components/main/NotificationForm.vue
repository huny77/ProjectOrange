<template>
  <div class="text-center">
    <v-menu offset-y open-on-hover :nudge-height="400" :nudge-width="600">
      <template v-slot:activator="{ on, attrs }">
          
        <v-btn 
          icon
          v-bind="attrs"
          v-on="on"
        >
          <v-badge
          :content="num"
          :value="num"
          color="green"
          overlap
        >
        <v-icon>mdi-bell-ring-outline</v-icon>
        </v-badge>
          
        </v-btn>
        
      </template>
      <v-list>
        <v-list-item-title class="mx-3 mt-3">
          <div class="d-flex">
            <v-icon>mdi-email-open-outline</v-icon>
            <v-flex class="mx-3">알림</v-flex>
          </div>
        </v-list-item-title>
        <v-divider></v-divider>
        <v-list-item
          v-for="(item, index) in items"
          :key="index"
        >
          <v-list-item-content>{{ item.notificationContent }}</v-list-item-content>
        </v-list-item>
      </v-list>
    </v-menu>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'NotificationForm',
  data() {
    return {
      items: [],
      num: "",
      show: false,
    }
  },
  methods: {
    async notificationItem() {
      try {
        const userId = this.$store.state.data2
        const { data } = await axios.get(`http://i5b102.p.ssafy.io:8181/api/notification/select/${userId}`)
        // console.log(data)
        this.items = data
        this.num = this.items.length;
      } catch (err) {
        console.log(err)
      }
    }
  },
  created() {
    this.notificationItem()
  }
}
</script>