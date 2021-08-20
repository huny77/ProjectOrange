<template>
  <div>
    <b-navbar variant="navbar-light bg-light justify-content-between px-5" type="light">
      <b-navbar-brand tag="h1">
        <v-img 
          src='@/assets/images/logo.png'
          width='100'
          height='50'
          @click="goHome"
        >
        </v-img>
      </b-navbar-brand>
      <b-navbar-nav class="d-flex">
        <b-nav-item>
          <v-text-field
            prepend-icon="mdi-magnify"
            v-model="title"
            cache-items
            class="mx-4"
            flat
            hide-no-data
            hide-details
            hide-selected
            label="야 너두 할 수 있어!"
            solo
            @keyup.enter="goSearch()"
          >
          </v-text-field>
          
        </b-nav-item>
        <b-nav-item>
          <v-tooltip bottom>
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                icon
                v-bind="attrs"
                v-on="on"
                @click="goCreateChallenge"
              >
                <v-icon dark>
                  mdi-trophy
                </v-icon>
              </v-btn>
            </template>
            <span>챌린지 생성</span>
          </v-tooltip>
        </b-nav-item>
        
        <b-nav-item v-if="isUserLogin">
          <NotificationForm />
        </b-nav-item>
        
        <b-nav-item>
          <v-tooltip bottom>
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                icon
                v-bind="attrs"
                v-on="on"
                @click="goProfile"
              >
                <v-icon dark>
                  mdi-account
                </v-icon>
              </v-btn>
            </template>
            <span>프로필</span>
          </v-tooltip>
        </b-nav-item>
      </b-navbar-nav>
    </b-navbar>
  </div>
</template>

<script>
import NotificationForm from '@/components/main/NotificationForm.vue'

export default {
  name: 'Navbar',
  components: {
    NotificationForm,
  },
  data () {
    return {
      items: [],
      title: '',
      model: null,
    }
  },
  methods: {
    goProfile() {
      this.$router.push({path:'/profile'}).catch(()=> {});
    },
    goHome() {
      this.$router.push({path:'/'}).catch(()=> {});
    },
    goCreateChallenge() {
      this.$router.push({path:'/create-challenge'}).catch(()=> {});
    },
    goSearch() {
      this.$router.push({path:`/search/${this.title}`}).catch(()=> {});
    }
  },
  computed: {
    isUserLogin() {
      return this.$store.getters.isLogin;
    }
  }
}
</script>

<style>

</style>