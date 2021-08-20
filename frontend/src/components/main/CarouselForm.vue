<template>
  <v-carousel
    cycle
    height="400"
    hide-delimiter-background
    show-arrows-on-hover
  >
    <v-carousel-item
      v-for="(item, i) in items"
      :key="i"
      :src="item.src"
    >
      <v-sheet>
        <v-row
          class="fill-height"
          align="center"
          justify="center"
        >
        </v-row>
      </v-sheet>
      <template v-slot:placeholder>
        <v-row
          class="fill-height ma-0"
          align="center"
          justify="center"
        >
          <v-progress-circular
            indeterminate
            color="grey lighten-5"
          ></v-progress-circular>
        </v-row>
      </template>
    </v-carousel-item>
  </v-carousel>
</template>

<script>
import carousel1 from '@/assets/images/carousel1_long.jpg'
import carousel3 from '@/assets/images/carousel3_long.jpg'
import carousel6 from '@/assets/images/carousel6_gold_long.jpg'
export default {
  data () {
    return {
       items: [
         {
           src: carousel1,
         },
         {
           src: carousel3,
         },
         {
           src: carousel6,
         },
       ]
    }
  },
}
</script>
<style>
.carousel_img {
  width: 100%;
  max-height: 100%;
}
</style>