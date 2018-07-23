import CreatingEventComponent from '../components/CreatingEventComponent';
import CreatePatternComponent from '../components/CreatePatternComponent';
import EventComponent from '../components/EventComponent';
import EventsComponent from '../components/EventsComponent';
import LogInComponent from '../components/LogInComponent';
import PatternComponent from '../components/PatternComponent';
import SignUpComponent from '../components/SignUpComponent';
import SignUpFinishComponent from '../components/SignUpFinishComponent';

export default [
  {path: '/events/new', component: CreatingEventComponent},
  {path: '/events/:id/pattern/new', component: CreatePatternComponent},
  {path: '/events/:id/pattern', component: PatternComponent},
  {path: '/events/:id', component: EventComponent},
  {path: '/events', component: EventsComponent},
  {path: '/signup/finish', component: SignUpFinishComponent},
  {path: '/signup', component: SignUpComponent},
  {path: '/login', component: LogInComponent},
  {path: '/', component: LogInComponent}
];
