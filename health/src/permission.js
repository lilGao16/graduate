import router from "./router";

router.beforeEach((to, from, next) => {

    const role = localStorage.getItem("role")
    console.log("------------" + role)
    if(!Object.is(to.name,"login")) {

      if (role) { // 判断当前的token是否存在 ； 登录存入的token
        next()
      } else {
        next('/login')
      }
    }
    else {

      next()
    }
  }
)
