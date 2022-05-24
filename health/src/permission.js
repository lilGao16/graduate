import router from "./router";

router.beforeEach((to, from, next) => {

    const token = localStorage.getItem("token")
    console.log("------------" + token)
    if (token) { // 判断当前的token是否存在 ； 登录存入的token
        next()
    } else {
      next({
        path: '/login'
      })
    }

  }
)
