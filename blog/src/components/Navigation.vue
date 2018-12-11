<template>
  <Row style="position: fixed; top: 0; background-color: rgba(229,229,229,0.68); width: 100%; height: 40px">
    <i-col span="1">
      <img src="../../static/image/logo.png" width="39px" style="margin-top: 1px" @click="router"/>
    </i-col>
    <i-col v-bind:span="b_span" v-bind:offset="b_offset" style="margin-top: 4px;">
      <Button type="default" to="/post">W R I T E</Button>
    </i-col>
  </Row>
</template>

<script>
  import ICol from "iview/src/components/grid/col"
  import router from '../router'
  import global from '../../static/Global'

  export default {
    data() {
      return {
        b_offset: 20,
        b_span: 3
      }
    },
    components: {ICol, global},
    name: "navigation",
    methods: {
      router: () => {
        router.push('/')
      }
    },
    mounted() {
      let screenWidth = global.screenWidth
      if (screenWidth < 400) {
        this.b_offset = 17
        this.b_span = 3
      }
      else if (screenWidth < 500) {
        this.b_offset = 18
        this.b_span = 3
      }
      else if (screenWidth < 1100) {
        this.b_offset = 20
        this.b_span = 3
      }
      else {
        this.b_offset = 21
        this.b_span = 2
      }
    }
  }
</script>

<style scoped>

</style>
