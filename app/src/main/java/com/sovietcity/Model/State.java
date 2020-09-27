package com.sovietcity.Model;

/**
 * Created by Серёга on 28.06.2016.
 */
public enum State {
    RUN_ONLY_SURFACE {
        @Override
        public State pause() {
            return this;
        }

        @Override
        public State play() {
            return State.RUN_ALL;
        }

        @Override
        public State openMenu() {
            return State.RUN_AFTER_MENU_SURFACE;
        }

        @Override
        public State closeMenu() {
            return this;
        }

        @Override
        public boolean runSurfaceView() {
            return true;
        }

        @Override
        public boolean runTimeThread() {
            return false;
        }
    },
    RUN_AFTER_MENU_ALL {
        @Override
        public State pause() {
            return RUN_AFTER_MENU_SURFACE;
        }

        @Override
        public State play() {
            return RUN_AFTER_MENU_ALL;
        }

        @Override
        public State openMenu() {
            return RUN_AFTER_MENU_ALL;
        }

        @Override
        public State closeMenu() {
            return State.RUN_ALL;
        }

        @Override
        public boolean runSurfaceView() {
            return false;
        }

        @Override
        public boolean runTimeThread() {
            return false;
        }
    },
    RUN_AFTER_MENU_SURFACE {
        @Override
        public State pause() {
            return RUN_AFTER_MENU_SURFACE;
        }

        @Override
        public State play() {
            return RUN_AFTER_MENU_ALL;
        }

        @Override
        public State openMenu() {
            return RUN_AFTER_MENU_SURFACE;
        }

        @Override
        public State closeMenu() {
            return State.RUN_ONLY_SURFACE;
        }

        @Override
        public boolean runSurfaceView() {
            return false;
        }

        @Override
        public boolean runTimeThread() {
            return false;
        }
    },
    RUN_ALL {
        @Override
        public State pause() {
            return RUN_ONLY_SURFACE;
        }

        @Override
        public State play() {
            return RUN_ALL;
        }

        @Override
        public State openMenu() {
            return State.RUN_AFTER_MENU_ALL;
        }

        @Override
        public State closeMenu() {
            return RUN_ALL;
        }

        @Override
        public boolean runSurfaceView() {
            return true;
        }

        @Override
        public boolean runTimeThread() {
            return true;
        }
    },
    RUN_SURFACE_ONCE {
        @Override
        public State pause() {
            return RUN_AFTER_MENU_SURFACE;
        }

        @Override
        public State play() {
            return RUN_AFTER_MENU_ALL;
        }

        @Override
        public State openMenu() {
            return RUN_AFTER_MENU_ALL;
        }

        @Override
        public State closeMenu() {
            return RUN_ALL;
        }

        @Override
        public boolean runSurfaceView() {
            return true;
        }

        @Override
        public boolean runTimeThread() {
            return false;
        }
    };
    public abstract State pause();
    public abstract State play();
    public abstract State openMenu();
    public abstract State closeMenu();
    public abstract boolean runSurfaceView();
    public abstract boolean runTimeThread();
}
