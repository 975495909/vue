<template>

  <router-view  v-if="isRouterAlive"/>
</template>

<style>

</style>
<script>
export default {
 name: 'App',
 provide () {
   return{
     reload: this.reload
   }
 },
 data() {
   return {
       isRouterAlive: true
   }
 },
 methods:{
   reload(){
     this.isRouterAlive = false
     this.$nextTick(function(){
       this.isRouterAlive = true
     })
   }
 }
}
</script>