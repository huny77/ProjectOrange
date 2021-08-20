import Vue from "vue";
import VueRouter from "vue-router";
import store from '@/store/index'

Vue.use(VueRouter);

const routes = [
  {
    path: '/authentication',
    component: () => import('@/layouts/authentication/Index.vue'),
    children: [
      {
        path: 'login',
        name: 'Login',
        component: () => import('@/views/user/LoginPage.vue'),
      },
      {
        path: 'sign-up',
        name: 'Signup',
        component: () => import('@/views/user/SignupPage.vue'),
      },
    ]
  },
  {
    path: '/',
    component: () => import('@/layouts/main/Index.vue'),
    children: [
      {
        path: '',
        name: 'IndexFeed',
        component: () => import('@/views/feed/IndexFeed.vue'),
      },
      {
        path: 'profile',
        name: 'Profile',
        component: () => import('@/views/user/Profile.vue'),
        meta: { auth: true },
      },
      {
        path: 'create-challenge',
        name: 'CreateChallenge',
        component: () => import('@/views/feed/CreateChallenge.vue'),
        meta: { auth: true },
      },
      {
        path: 'challenge/:id',
        name: 'ChallengeFeed',
        component: () => import('@/views/feed/ChallengeFeed.vue'),
        meta: { auth: true },
      },
      {
        path: 'create-feed',
        name: 'CreateFeed',
        component: () => import('@/views/feed/CreateFeed.vue'),
        meta: { auth: true },
      },
      {
        path: 'feed/:id',
        name: 'FeedDetail',
        component: () => import('@/views/feed/FeedDetail.vue'),
        meta: { auth: true },
      },
      {
        path: 'ranking',
        name: 'Ranking',
        component: () => import('@/views/feed/Ranking.vue'),
        meta: { auth: true },
      },
      {
        path: 'search/:title',
        name: 'Search',
        component: () => import('@/views/feed/Search.vue'),
      },
    ]
  },
  {
    path: '*',
    redirect: '/NotFoundPage'
  },
  {
    path: '/NotFoundPage',
    component: () => import('@/views/user/NotFoundPage.vue'),
  },
  // kakao callback
  // {
  //   path: '/auth/kakao/callback',
  //   name: 'AuthKakaoCallback',
  //   component: () => import('@/views/user/AuthKakaoCallback.vue')
  // },

];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach((to, from, next) => {
  if (to.meta.auth && !store.getters.isLogin ) {
    console.log('인증이 필요합니다')
    next('/authentication/login')
    return;
  }
  next();
})

export default router;
