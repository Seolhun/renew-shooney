const commonMixin = {
  changeFullScreen (targetElement) {
    let htmlElement = this.$el.querySelector(targetElement)
    htmlElement.style.overflow = 'visible'

    this._fullScreenElement(htmlElement)
  },

  _fullScreenElement (htmlElement) {
    this._autoHeightStyleByElement(htmlElement)

    if (htmlElement.requestFullscreen) {
      htmlElement.requestFullscreen();
    } else if (htmlElement.mozRequestFullScreen) {
      htmlElement.mozRequestFullScreen();
    } else if (htmlElement.webkitRequestFullscreen) {
      htmlElement.webkitRequestFullscreen();
    } else if (htmlElement.msRequestFullscreen) {
      htmlElement.msRequestFullscreen();
    }
  },

  autoHeightStyleByEvent (event) {
    let h = window.innerHeight;
    event.target.style.width = '100%'
    event.target.style.height = '100%'
    event.target.style.minHeight = h + 300
    event.target.style.overflow = 'auto'
  },

  _autoHeightStyleByElement (htmlElement) {
    let h = window.innerHeight;
    htmlElement.style.width = '100%'
    htmlElement.style.height = '100%'
    htmlElement.style.minHeight = h + 300
    htmlElement.style.overflow = 'auto'
  }
}

export {commonMixin}
