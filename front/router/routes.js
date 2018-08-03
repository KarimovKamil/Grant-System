import ApplicationComponent from '../pages/applications/_id';
import ApplicationsComponent from '../pages/applications/index';
import CreatingEventComponent from '../pages/events/new';
import CreatePatternComponent from '../pages/events/_id/pattern/new';
import EventComponent from '../pages/events/_id/index';
import EventsComponent from '../pages/events/index';
import LogInComponent from '../components/LogInComponent';
import PatternComponent from '../pages/events/_id/pattern/index';
import SignUpComponent from '../pages/signup/index';
import SignUpFinishComponent from '../pages/signup/finish';

export default [
  {path: '/signup/finish', component: SignUpFinishComponent},
];
