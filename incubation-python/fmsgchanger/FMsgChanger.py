# -*- coding: utf-8 -*-

# test using kivy to display output for 2-foo-3-bar-6-foobar program 
# 1. output to console first
# 2. output results progressively to Kivy App
# also serving as a test to separate MVC - delegate Model and View (and minimal Controls) to kv file

from kivy.app import App
from kivy.uix.boxlayout import BoxLayout

from fmsgchanger.fmsg import FMsg


class FMsgChangerScreen(BoxLayout):

    def __init__(self, **kwargs):
        super(FMsgChangerScreen, self).__init__(**kwargs)
        self.init_view(1)

    def set_view(self, screen_id):
        if screen_id == 1:  # FMsgChanger
            self.ids.title.text = FMsg.get_msg("msg_0001")
            self.ids.btn.text = FMsg.get_msg("msg_0002")
            self.ids.subtext.text = FMsg.get_msg("msg_0003")
        else:
            print("screen id unidentified: " + screen_id)

    def init_view(self, screen_id):
        if screen_id == 1:  # FMsgChanger
            self.ids.btn_jp.text = u"日本語へ"
            self.set_view(screen_id)
        else:
            print("screen id unidentified: " + screen_id)

    @staticmethod
    def change_lang(lang_id):
        FMsg.set_lang(lang_id)


class FMsgChangerApp(App):
    def build(self):
        return FMsgChangerScreen()


if __name__ == "__main__":
    FMsgChangerApp().run()
